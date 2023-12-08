package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ayawow
 * @since 2023-12-04
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods Goods){
        return goodsService.save(Goods) ? Result.success() : Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Goods Goods){
        return goodsService.updateById(Goods) ? Result.success() : Result.fail();
    }

    //删
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id){
        return goodsService.removeById(id) ? Result.success() : Result.fail();
    }

    //查
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodstype");

        Page<Goods> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goods::getName,name);
        }

        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            lambdaQueryWrapper.eq(Goods::getStorage,storage);
        }

        if(StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)){
            lambdaQueryWrapper.eq(Goods::getGoodstype,goodstype);
        }

        IPage result = goodsService.pageCC(page,lambdaQueryWrapper);


        return Result.success(result.getRecords(),result.getTotal());
    }


}
