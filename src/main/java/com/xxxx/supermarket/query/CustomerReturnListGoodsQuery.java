package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class CustomerReturnListGoodsQuery extends BaseQuery {
    private Integer customerReturnListId;
}
