package com.baidu.fbu.asset.controller;

import com.baidu.fbu.asset.entity.AssetManager;
import com.baidu.fbu.asset.service.AssetManagerService;
import com.baidu.fbu.asset.service.AssetPlanService;
import com.baidu.fbu.asset.util.BaseController;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.common.service.FormatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/assetManager")
public class AssetManagerController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AssetManagerController.class);

    @Resource
    private AssetManagerService assetManagerService;

    @Resource
    private AssetPlanService assetPlanService;

    /**
     * 查询 AssetManager 结果集
     */
    @RequestMapping("/findAssetManager")
    public void findAssetManager(AssetManager assetManager, Integer page, Integer pageSize, HttpServletResponse rep) {
        // 查询第几页
        page = PageUtil.handlePage(page);

        // 每页的行数，方法的第二个参数表示查询的是哪类对象
        pageSize = PageUtil.handlePageSize(pageSize, "AssetManager");

        // 查询起始行的行数
        int startRow = PageUtil.calculateStartRow(page, pageSize);

        Map<String, Object> resultMap = null;

        try {
            resultMap = assetManagerService.findByParam(assetManager, startRow, pageSize);
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetManager error"), e);
        }

        resultMap.put("message", "success");
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("assetManager", assetManager);    // assetManager 存放条件查询的参数
        IOUtil.writeToPage(rep, resultMap);
    }

    /**
     * 添加 AssetManager
     */
    @RequestMapping("/addAssetManager")
    public void addAssetManager(AssetManager assetManager, HttpServletResponse rep) {
        try {
            assetManagerService.add(assetManager);
            IOUtil.writeToPage(rep, "message", "success");
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("addAssetManager error"), e);
            IOUtil.writeToPage(rep, "message", "添加失败");
        }
    }

    /**
     * 查询修改页所需的 AssetManager 的信息
     */
    @RequestMapping("/findAssetManagerById")
    public void findAssetManagerById(AssetManager assetManager, HttpServletResponse rep) {
        AssetManager result = null;

        try {
            result = assetManagerService.findById(assetManager.getId());
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetManagerById error"), e);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("message", "success");
        resultMap.put("assetManager", result);
        IOUtil.writeToPage(rep, resultMap);
    }

    /**
     * 查询修改页所需的 AssetManager 和相关的信息
     */
    @RequestMapping("/findAssetManagerWithRelatedInfo")
    public void findAssetManagerWithRelatedInfo(AssetManager assetManager, HttpServletResponse rep) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = assetManagerService.findAssetManagerWithRelatedInfo(assetManager.getId());
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetManagerWithRelatedInfo error"), e);
        }

        resultMap.put("message", "success");
        IOUtil.writeToPage(rep, resultMap);
    }

    /**
     * 修改 AssetManager
     */
    @RequestMapping("/updateAssetManager")
    public void updateAssetManager(AssetManager assetManager, HttpServletResponse rep) {
        try {
            assetManagerService.update(assetManager);
            IOUtil.writeToPage(rep, "message", "success");  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("updateAssetManager error"), e);
            IOUtil.writeToPage(rep, "message", e.getMessage());
        }
    }

    /**
     * 删除 AssetManager
     */
    @RequestMapping("/deleteAssetManager")
    public void deleteAssetManager(AssetManager assetManager, HttpServletResponse rep) throws SQLException {
        try {
            assetManagerService.deleteAssetManager(assetManager);
            IOUtil.writeToPage(rep, "message", "success");   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("deleteAssetManager error"), e);
            IOUtil.writeToPage(rep, "message", e.getMessage());
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("deleteAssetManager error"), e);
            IOUtil.writeToPage(rep, "message", e.getMessage());
        }
    }

}