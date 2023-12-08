package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Storage;
import com.wms.entity.Storage;
import com.wms.mapper.StorageMapper;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ayawow
 * @since 2023-12-01
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;


    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage){
        return storageService.save(storage) ? Result.success() : Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage){
        return storageService.updateById(storage) ? Result.success() : Result.fail();
    }

    //删
    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id){
        return storageService.removeById(id) ? Result.success() : Result.fail();
    }

    //查
    @PostMapping("/list")
    public Result list(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");

        Page<Storage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }


        IPage result = storageService.pageCC(page,lambdaQueryWrapper);


        return Result.success(result.getRecords(),result.getTotal());
    }

    @GetMapping("/ls")
    public Result ls(){
        List list = storageService.list();
        return Result.success(list);
    }

}
