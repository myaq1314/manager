package com.baidu.fbu.asset.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.service.AssetDetailService;
import com.baidu.fbu.asset.service.AssetPlanService;
import com.baidu.fbu.asset.util.BaseController;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;

@Controller
@RequestMapping("/assetDetail")
public class AssetDetailController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AssetDetailController.class);

    /** 字符编码 */
    private static final String DEFAULT_CHARSET = "UTF-8";

    @Resource
    private AssetDetailService assetDetailService;

    @Resource
    private AssetPlanService assetPlanService;

    /** 查询 AssetDetail 结果集 */
    @RequestMapping("/findAssetDetail")
    public void findAssetDetail( AssetDetail assetDetail, Integer page, Integer pageSize,
                                                            HttpServletResponse rep ) {
        // 查询第几页
        page = PageUtil.handlePage( page );

        // 每页的行数，方法的第二个参数表示查询的是哪类对象
        pageSize = PageUtil.handlePageSize( pageSize, "AssetDetail" );

        // 查询起始行的行数
        int startRow = PageUtil.calculateStartRow(page, pageSize);

        Map<String, Object> resultMap = null;
        try {
            resultMap = assetDetailService.findByParamWithSumInfo( assetDetail, startRow, pageSize );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findByParamWithSumInfo error"), e);
        }

        resultMap.put( "message", "success" );
        resultMap.put( "page", page );
        resultMap.put( "pageSize", pageSize );
        resultMap.put( "assetDetail", assetDetail );    // assetDetail 存放条件查询的参数

        IOUtil.writeToPage( rep, resultMap );
    }

    /** 添加 AssetDetail */
    @RequestMapping("/addAssetDetail")
    public void addAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.add( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("addAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 查询修改页面所需的 AssetDetail 的信息 */
    @RequestMapping("/findAssetDetailById")
    public void findAssetDetailById( AssetDetail assetDetail, HttpServletResponse rep ) {
        AssetDetail result = null;

        try {
            result = assetDetailService.findById( assetDetail.getId() );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetDetailById error"), e);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put( "message", "success" );
        resultMap.put( "assetDetail", result );
        IOUtil.writeToPage( rep, resultMap );
    }

    /** 查询修改页所需的 AssetDetail 和相关的信息 */
    @RequestMapping("/findAssetDetailWithRelatedInfo")
    public void findAssetDetailWithRelatedInfo( AssetDetail assetDetail, HttpServletResponse rep ) {
        Map<String, Object> resultMap = null;
        try {
            resultMap = assetDetailService.findAssetDetailWithRelatedInfo( assetDetail.getId() );
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("findAssetDetailWithRelatedInfo error"), e);
        }

        resultMap.put( "message", "success" );
        IOUtil.writeToPage( rep, resultMap );
    }

    /** 修改 AssetDetail */
    @RequestMapping("/updateAssetDetail")
    public void updateAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.update( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );  // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (Exception e) {
            LOG.error(FormatService.logFormat("updateAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 删除 AssetDetail */
    @RequestMapping("/deleteAssetDetail")
    public void deleteAssetDetail( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.deleteById( assetDetail.getId() );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("deleteAssetDetail error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 批量将 AssetDetail 添加到 AssetPlan */
    @RequestMapping("/batchAddAssetDetailToAssetPlan")
    public void batchAddAssetDetailToAssetPlan(String ids, AssetDetail assetDetail, HttpServletResponse rep) {
        try {
            assetDetailService.batchAddAssetDetailToAssetPlan(ids, assetDetail.getApId());
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("batchAddAssetDetailToAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 将符合条件的全部 AssetDetail 添加到 AssetPlan */
    @RequestMapping("/addAllAssetDetailToAssetPlan")
    public void addAllAssetDetailToAssetPlan(Long apIdParam, AssetDetail assetDetail, HttpServletResponse rep) {
        try {
            assetDetailService.addAllAssetDetailToAssetPlan(assetDetail, apIdParam);
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("addAllAssetDetailToAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 删除 AssetPlan 中的 AssetDetail */
    @RequestMapping("/deleteAssetDetailInAssetPlan")
    public void deleteAssetDetailInAssetPlan( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.deleteAssetDetailInAssetPlan( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(FormatService.logFormat("deleteAssetDetailInAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 删除 AssetPlan 中所有的 AssetDetail */
    @RequestMapping("/deleteAllAssetDetailInAssetPlan")
    public void deleteAllAssetDetailInAssetPlan( AssetDetail assetDetail, HttpServletResponse rep ) {
        try {
            assetDetailService.deleteAllAssetDetailInAssetPlan( assetDetail );
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error(FormatService.logFormat("deleteAllAssetDetailInAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 批量删除 AssetPlan 中的  AssetDetail */
    @RequestMapping("/batchDeleteAssetDetailInAssetPlan")
    public void batchDeleteAssetDetailInAssetPlan(String ids, HttpServletResponse rep) {
        try {
            assetDetailService.batchDeleteAssetDetailInAssetPlan(ids);
            IOUtil.writeToPage( rep, "message", "success" );   // 页面里根据这个操作成功的标志进行后续的跳转
        } catch (SQLException e) {
            LOG.error(FormatService.logFormat("batchDeleteAssetDetailInAssetPlan error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }
    }

    /** 导出 资产计划 相关的 资产明细 到 excel
     * @throws IOException */
    @RequestMapping("/exportAssetDetailOfAnAssetPlan")
    public void exportAssetDetailOfAnAssetPlan(AssetDetail assetDetail,
            HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = null;
        try {
            workbook = assetDetailService.generateAssetToExcel(assetDetail);
            String apName = "资产计划-" + assetPlanService.getPlanName(assetDetail.getApId()); // 该资产计划名称
            response.reset();
            response.setCharacterEncoding(DEFAULT_CHARSET);
            response.setContentType("application/vnd.ms-excel; charset=" + DEFAULT_CHARSET);
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(apName.getBytes("gb2312"), "iso8859-1")
                    + Util.getDateStringOfToday()
                    + ".xls"); // 设置文件头编码方式和文件名
            workbook.write(response.getOutputStream());
        } catch (RuntimeException e) {
            LOG.error(FormatService.logFormat("exportAssetDetailOfAnAssetPlan error."), e);
        } catch (IOException e) {
            LOG.error(FormatService.logFormat("exportAssetDetailOfAnAssetPlan error."), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    /** 导入 excel 确定资产管理人要购买的资产 */
    @RequestMapping("/importExcelToSellAsset")
    public void importExcelToSellAsset( @RequestParam MultipartFile file,
                                    HttpServletResponse rep, HttpServletRequest req) {
        long apId = Long.valueOf(req.getParameter("apId"));
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring( 0, fileName.length() - 4 ) + "_" + Util.getRandomString( 10 ) + ".xls";
        String uploadPath = req.getSession().getServletContext().getRealPath("/");
        uploadPath += File.separator ; // +"upload"+ File.separator;
        // Util.print( uploadPath+" == "+ fileName );

        // 上传文件到服务器
        File uploadFile = new File(uploadPath + "/" + fileName);
        try {
            file.transferTo(uploadFile);
        } catch (IllegalStateException e) {
            LOG.error(FormatService.logFormat("importExcelToSellAsset-uploadFile"), e);
        } catch (IOException e) {
            LOG.error(FormatService.logFormat("importExcelToSellAsset-uploadFile"), e);
        }
        int status = 0;
        String statusMessage = "";

        /** 导入借据 excel 到数据库 */
        try {
            status = assetDetailService.excelInAsset(apId, uploadFile);
        } catch (RuntimeException e) {
            LOG.error(FormatService.logFormat("importExcelToSellAsset error"), e);
            IOUtil.writeToPage( rep, "message", e.getMessage() );
        }

        // 删除文件
        IOUtil.deleteFile(uploadPath, fileName);

        switch (status) {
            case 1: // 1 表示文件上传成功，资产全部转让
                statusMessage = "success";
                break;
            case 2: // 2 借据号不属于该计划
                statusMessage = "match";
                break;
            case 3: // 3 借据号无意义
                statusMessage = "notFound";
                break;
            case 4: // 4 文件上传失败，IO输出异常
                statusMessage = "ioException";
                break;
            case 5: // 5 找不到文件
                statusMessage = "pageNull";
                break;
            case 6: // 6 有未填项
                statusMessage = "loanIdNull";
                break;
            default :
                break;
        }
        IOUtil.writeToPage(rep, "message", statusMessage);
    }

}