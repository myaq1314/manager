package com.baidu.fbu.asset.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.dao.AssetManagerDao;
import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.AssetPlan;
import com.baidu.fbu.asset.service.AssetPlanService;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;

@Service("assetPlanService")
@Transactional
public class AssetPlanServiceImpl implements AssetPlanService {
    private static final Logger LOG = LoggerFactory.getLogger(AssetPlanServiceImpl.class);

    @Resource
    private AssetPlanDao assetPlanDao;

    @Resource
    private AssetDetailDao assetDetailDao;

    @Resource
    private AssetManagerDao assetManagerDao;

    public void add( AssetPlan assetPlan ) throws SQLException {
        // 页面输入的 合同资产总额 的单位是 万元，所以需要转换一下
        assetPlan.setTotalAmount(assetPlan.getTotalAmount().multiply(new BigDecimal(10000)));

        // String dealId = UUID.randomUUID().toString();
        String dealId = String.valueOf(System.currentTimeMillis())
                                                + String.valueOf(new Random().nextInt(1000));
        assetPlan.setDealId(dealId);
        Date now = new Date();
        assetPlan.setUpdatetime(now);
        assetPlan.setCreatetime(now);
        assetPlanDao.addByParam(assetPlan);
    }

    public void update(AssetPlan assetPlan) throws SQLException {
        // 页面输入的 合同资产总额 的单位是 万元，所以需要转换一下
        assetPlan.setTotalAmount(assetPlan.getTotalAmount().multiply(new BigDecimal(10000)));
        assetPlan.setUpdatetime(new Date());
        assetPlanDao.updateByParam(assetPlan);
    }

    public void deleteById( Long id ) throws SQLException {
        assetPlanDao.deleteById(id);
    }

    public AssetPlan findById(Long id) throws SQLException {
        AssetPlan assetPlan = (AssetPlan) assetPlanDao.findById(id);
        // 页面输入的 合同资产总额 的单位是 万元，所以需要转换一下
        assetPlan.setTotalAmount(assetPlan.getTotalAmount().divide(new BigDecimal(10000)));
        return assetPlan;
    }

    /** 查询符合条件的记录的条数  */
    public Long countByParam(AssetPlan assetPlan) throws SQLException {
        return assetPlanDao.countByParam( assetPlan );
    }

    /** 查询符合条件的记录  */
    public Map<String, Object> findByParam( AssetPlan assetPlan ) throws SQLException {
        // 传递参数 0, 0 表示 查询全部结果；分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        return findByParam( assetPlan, 0, 0 );
    }

    /** 查询符合条件的记录，分页  */
    public Map<String, Object> findByParam( AssetPlan assetPlan, int startRow, int pageSize ) throws SQLException {
        //  查询符合条件的条数
        long count = assetPlanDao.joinCountByParam(assetPlan);

        Map<String, Object> map = IOUtil.beanToMap(assetPlan);
        map.put( "startRow", startRow );
        map.put( "pageSize", pageSize );

        List<Object> list = assetPlanDao.joinFindByParam(map);

        // 遍历结果集，根据 资产计划 的发行时间，更新 资产计划 的状态
        List<Object> publishedAssetPlanList = findPublishedAssetPlans( list );

        // 将结果集转换为 json 往浏览器回写时，把 null 转换为 Date 类型会报错。这时输出一个空字符串
        List<Object> keyList = Util.getArrayList();
        keyList.add( "handoverDate" );
        keyList.add( "publishDate" );
        IOUtil.formatDateToStr(list, keyList);

        // 格式化 totalAmount 的单位 为 万元
        IOUtil.formatMoney(list, "totalAmount");

        // 更新那些 已经到了发行日期的 计划的 计划状态
        if ( publishedAssetPlanList.size() > 0 ) {
            Map<String, Object> param = Util.getHashMap();
            param.put("planStatus", 1);      // 计划状态       1=发行  2=撤销   0=待发行
            param.put("publishedAssetPlanList", publishedAssetPlanList);
            param.put("updatetime", new Date());
            assetPlanDao.batchUpdateByParam( param );
        }

        Map<String, Object> result = Util.getHashMap();

        result.put( "list", list );
        result.put( "count", count );

        // 分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        // 如果是分页查询，还需要返回页面：有多少页的查询结果
        if ( startRow > -1 && pageSize > 0 ) {
            long howManyPages = PageUtil.calculateHowManyPages(count, pageSize);
            result.put( "howManyPages", howManyPages );
        }

        return result;
    }

    /** 遍历结果集，根据 资产计划 的发行时间，找出哪些 资产计划 的状态需要更新为 已发行 */
    public List<Object> findPublishedAssetPlans(List<Object> list) throws SQLException {
        List<Object> publishedAssetPlanList = Util.getArrayList();  // 更新这个列表中的 资产计划 的状态 为 已发行

        for (Iterator<Object> it = list.iterator(); it.hasNext();) {
            Map<String, Object> result = (Map<String, Object>) it.next();

            // 计划状态      0=待发行   1=已发行  2=撤销
            if ((Integer) result.get("planStatus") == 0 && result.get("publishDate") != null) {
                // 这个计划是否已经到了发行的日期
                int isPublished = new Date().compareTo( (Date) result.get("publishDate"));

                if (isPublished >= 0) {
                    String apId = result.get("id").toString();

                    Map<String, Object> param = Util.getHashMap();
                    param.put("apId", apId);
                    long numberOfAsset = assetDetailDao.countByParam(param);

                    // 判断资产计划中的 资产明细数量，如果是 0，那这个计划就不能发行
                    if (numberOfAsset > 0) {
                        // param.put("transferStatus", 1);    //  资产转让状态    0=未转让   1=待转让   2=已转让
                        // long numberOfNotTransferredAssets = assetDetailDao.countByParam(param);

                        // 并且 判断资产计划中 待转让的资产明细数量，如果大于 0，那这个计划就不能发行
                        // if (numberOfNotTransferredAssets == 0) {
                        if (true) {
                            publishedAssetPlanList.add( apId );
                            result.put("planStatus", 1);     // 计划状态   1=已发行  2=撤销 0=待发行
                        }
                    }
                }
                // Util.print(  "========="+ publishedAssetPlanList );
            }
        }
        return publishedAssetPlanList;
    }

    /** 撤销 未发行的 资产计划 */
    public void cancel(Long id) throws SQLException {
        AssetPlan assetPlan = (AssetPlan) assetPlanDao.findById(id);

        // 修改 计划状态
        assetPlan.setPlanStatus((short) 2);   // 计划状态    2=撤销   0=待发行   1=发行
        assetPlan.setUpdatetime(new Date());
        assetPlanDao.updateByParam(assetPlan);

        // 删除 资产计划 中的 资产
        Map<String, Object> map = Util.getHashMap();
        map.put( "apId", id );
        map.put( "transferStatus", 0 );    // 更新 资产明细的状态   0 = 未转让   1 = 待转让   2 = 已转让
        map.put( "updatetime", new Date() );
        assetDetailDao.batchUpdateAssetDetailByAssetPlanId(map);
    }

    public Map<String, Object> findAssetPlanWithRelatedInfo(Long id) throws SQLException {
        Map<String, Object> map = Util.getHashMap();
        if ( id != null ) {
            map.put("assetPlan", findById(id));
        }
        map.put("idAndNameList", assetPlanDao.findIdAndNameOfAssetManager());
        return map;
    }

    public String getPlanName(Long apId) {
        String name = "";
        try {
            name = assetPlanDao.getPlanName(apId);
        } catch (DataAccessException e) {
            LOG.error("查询 tbl_asset_plan 表失败");
        }
        return name;
    }

}