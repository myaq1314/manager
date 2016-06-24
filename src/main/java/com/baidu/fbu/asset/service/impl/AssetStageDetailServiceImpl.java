package com.baidu.fbu.asset.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.dao.AssetStageDetailDao;
import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.entity.vo.AssetStageDetailVo;
import com.baidu.fbu.asset.service.AssetStageDetailService;
import com.baidu.fbu.asset.util.ExcelUtil;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;

@Service("assetStageDetailService")
@Transactional
public class AssetStageDetailServiceImpl implements AssetStageDetailService {
    /** log日志   */
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AssetDetailServiceImpl.class);

    @Resource
    private AssetStageDetailDao assetStageDetailDao;

    @Resource
    private AssetDetailDao assetDetailDao;

    public void add( AssetStageDetail assetStageDetail ) throws SQLException {
        assetStageDetail.setCreatetime(new Date());
        assetStageDetail.setUpdatetime(new Date());
        assetStageDetailDao.addByParam(assetStageDetail);
    }

    public void update(AssetStageDetail assetStageDetail) throws SQLException {
        assetStageDetail.setUpdatetime(new Date());
        assetStageDetailDao.updateByParam(assetStageDetail);
    }

    public void deleteById( Long id ) throws SQLException {
        assetStageDetailDao.deleteById(id);
    }

    public AssetStageDetail findById( Long id ) throws SQLException {
        return (AssetStageDetail) assetStageDetailDao.findById(id);
    }

    /** 查询符合条件的记录的条数  */
    public Long countByParam(AssetStageDetail assetStageDetail) throws SQLException {
        return assetStageDetailDao.countByParam( assetStageDetail );
    }

    /** 查询全部符合条件的记录  */
    public Map<String, Object> findByParam( AssetStageDetail assetStageDetail ) throws SQLException {
        // 传递参数 0, 0 表示 查询全部结果；分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        return findByParam( assetStageDetail, 0, 0 );
    }

    /**  分页查询符合条件的记录   */
    public Map<String, Object> findByParam(AssetStageDetail assetStageDetail,
                                           int startRow, int pageSize) throws SQLException {
        long count = assetStageDetailDao.countByParam(assetStageDetail);

        Map<String, Object> map = IOUtil.beanToMap(assetStageDetail);
        map.put("startRow", startRow);
        map.put("pageSize", pageSize);
        List<Object> list = assetStageDetailDao.findByParam(map);

        Map<String, Object> result = Util.getHashMap();
        result.put( "list", list );
        result.put( "count", count );

        // 分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        // 如果是分页查询，还需要返回页面：有多少页的查询结果
        if ( startRow > -1 && pageSize > 0 ) {
            long howManyPages = PageUtil.calculateHowManyPages(count, pageSize);
            result.put( "howManyPages", howManyPages );
        }

        // 查询 分期明细列表所需的 资产明细的 分期计划数 和 转让状态
        AssetDetail assetDetail = (AssetDetail) assetDetailDao.findById( assetStageDetail.getId() );
        Map<String, Object> sumMap = Util.getHashMap();
        sumMap.put("transferStatus", assetDetail.getTransferStatus());
        sumMap.put("howManyStage", assetDetail.getStageNo());
        result.put( "info", sumMap );

        return result;
    }

    public Map<String, Object> findAssetStageDetailByLoanIds(String ids) throws SQLException {
        Map<String, Object> resultMap = null;
        List<Object> resultList = Util.getArrayList();
        List<Object> assetStageList = null;

        // find asset stage destails by loanIds
        List<Object> idList = Util.idsStringToList(ids);

        for ( Iterator<Object> it = idList.iterator(); it.hasNext(); ) {
            String loanId = it.next().toString();

            Map<String, Object> map = Util.getHashMap();
            map.put("loanId", loanId);
            map.put("startRow", -1);
            map.put("pageSize", -1);

            assetStageList = assetStageDetailDao.findByParam(map);

            if (Util.isNullOrEmptyOrZero(assetStageList) != true) {
                resultList.addAll(assetStageList);
            }
            Util.print(resultList.size());
        }

        if (Util.isNullOrEmptyOrZero(resultList) != true) {
            resultMap = Util.getHashMap();
            resultMap.put("list", resultList);
            resultMap.put("count", resultList.size());
        }

        return resultMap;
    }

    public HSSFWorkbook generateAssetStageToExcel(String ids) {
        List<String> stageList = formatToList(ids);
        List<AssetStageDetailVo> list = new ArrayList<AssetStageDetailVo>();
        HSSFWorkbook listToExcel = null;
        try {
            list = assetStageDetailDao.exportAssetstages(stageList);
            listToExcel = ExcelUtil.exportAssetstage(list);
        } catch (DataAccessException e) {
            LOG.error(FormatService.logFormat("generateOrderToExcelByCon error."), e);
        }
        return listToExcel;
    }

    private static List<String> formatToList(String ids) {
        if (ids != null && ids != "") {
            String[] idArr = ids.trim().split(",");

            List<String> stageList = new ArrayList<String>();
            for (int i = 0; i < idArr.length; i++) {
                if (idArr[i] != null && idArr[i].trim() != "") {
                    stageList.add(idArr[i].trim());
                }
            }
            return stageList;
        }
        return null;
    }

}