package com.ebiz.computerdatabase.mapper;



import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * Created by ebiz on 20/06/17.
 */
public class PageRequestMapper {

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_ITEM_NUMBER = 10;
    public static final int DEFAULT_ORDER = 0;

    public static PageRequest pageRequestMapper(Map<String,String> map){

        int page = DEFAULT_PAGE;
        int  item_number = DEFAULT_ITEM_NUMBER;
        String sortBy = map.get("sort");
        String orderBy = map.get("orderby");


        if(  map.get("page") != null && !StringUtils.isNumeric(map.get("page"))){
            page = Integer.parseInt(map.get("page"));
        }

        if(  map.get("itemNumber") != null && !StringUtils.isNumeric(map.get("itemNumber"))){
            page = Integer.parseInt(map.get("itemNumber"));
        }


        if(sortBy !=null && sortBy.equals("0")) {
            return new PageRequest(page, item_number, new Sort(
                    new Sort.Order(Sort.Direction.ASC, orderBy)));

        }else if(sortBy!=null){
            return new PageRequest(page, item_number, new Sort(
                    new Sort.Order(Sort.Direction.DESC, orderBy)));
        }else{
            return new PageRequest(page, item_number);
        }

    }
}
