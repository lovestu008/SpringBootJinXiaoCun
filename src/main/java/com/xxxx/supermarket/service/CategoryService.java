package com.xxxx.supermarket.service;

import com.xxxx.supermarket.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 
 * @since 2023-03-04
 */
public interface CategoryService extends IService<Category> {

    Set<Integer> findGoodsByCategoryId(int id)throws Exception;
}
