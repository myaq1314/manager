package com.baidu.fbu.asset.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.fbu.asset.constants.Constants;
import com.baidu.fbu.asset.dao.AssetManagerDao;
import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.AssetManager;
import com.baidu.fbu.asset.entity.AssetPlan;
import com.baidu.fbu.asset.service.AssetManagerService;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;

@Service("assetManagerService")
@Transactional
public class AssetManagerServiceImpl implements AssetManagerService {
    @Resource
    private AssetManagerDao assetManagerDao;

    @Resource
    private AssetPlanDao assetPlanDao;

    public void add( AssetManager assetManager ) throws SQLException {
        assetManager.setCreatetime(new Date());
        assetManager.setUpdatetime(new Date());
        assetManagerDao.addByParam(assetManager);
    }

    public void update(AssetManager assetManager) throws SQLException {
        assetManager.setUpdatetime(new Date());
        assetManagerDao.updateByParam(assetManager);
    }

    public void deleteById( Long id ) throws SQLException {
        assetManagerDao.deleteById(id);
    }

    public void logicDeleteById( Long id ) throws SQLException {
        Map<String, Object> map = Util.getHashMap();
        map.put("id", id);
        map.put("updatetime", new Date());
        assetManagerDao.logicDeleteById(map);
    }

    public AssetManager findById( Long id) throws SQLException {
        return (AssetManager) assetManagerDao.findById(id);
    }

    /** 查询符合条件的记录的条数  */
    public Long countByParam(AssetManager assetManager) throws SQLException {
        return assetManagerDao.countByParam( assetManager );
    }

    /** 查询符合条件的记录  */
    public Map<String, Object> findByParam( AssetManager assetManager ) throws SQLException {
        // 传递参数 0, 0 表示 查询全部结果；分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        return findByParam( assetManager, 0, 0 );
    }

    /** 查询符合条件的记录，分页  */
    public Map<String, Object> findByParam( AssetManager assetManager,
                    int startRow, int pageSize ) throws SQLException {
        long count = assetManagerDao.countByParam(assetManager);

        Map<String, Object> map = IOUtil.beanToMap(assetManager);
        map.put( "startRow", startRow );
        map.put( "pageSize", pageSize );

        List<Object> list = assetManagerDao.findByParam(map);

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

    public void deleteAssetManager( AssetManager assetManager ) throws SQLException, Exception {
        Long amId = assetManager.getId();

        // 如果这个资产管理人有关联的资产计划，就不能将他删除
        List<AssetPlan> assetPlanList = assetPlanDao.findByAssetManageId( amId );

        if ( !Util.isNullOrEmptyOrZero( assetPlanList ) ) {
            throw new Exception("这个资产管理人有关联的资产计划，所以不能删除");
        } else {
            assetManagerDao.deleteById(amId);
        }
    }

    public Map<String, Object> findAssetManagerWithRelatedInfo(Long id) throws SQLException {
        Map<String, Object> result = Util.getHashMap();

        if ( id != null ) {
            AssetManager assetManager = (AssetManager) assetManagerDao.findById(id);
            result.put("assetManager", assetManager);
        }
        result.put("bankNameList", Constants.getBankNameList());

        return result;
    }

}