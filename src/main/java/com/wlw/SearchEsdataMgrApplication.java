package com.wlw;

import com.wlw.wlsearch.dto.SearchParam;
import com.wlw.wlsearch.entity.SearchResult;
import com.wlw.wlsearch.service.impl.StoreSearchServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class SearchEsdataMgrApplication {

    static Logger logger = LoggerFactory.getLogger(SearchEsdataMgrApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SearchEsdataMgrApplication.class, args);
        test(context);
    }

    private static void test(ConfigurableApplicationContext context) {
        StoreSearchServiceImpl storeSearchService = context.getBean("wl_store_search", StoreSearchServiceImpl.class);
        SearchParam searchParam = new SearchParam();
        searchParam.setContent("手机");
        Page<SearchResult> page = storeSearchService.search(searchParam);
        System.out.print(page.toString());
    }
}
