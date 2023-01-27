package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/searches")
public class SearchController {
    @Autowired
    private QueryDao queryDao;

    @GetMapping
    public Collection<Query> getQueriesBetweenTimestamps(@RequestParam Timestamp mini,
            @RequestParam Timestamp maxi) {

        return queryDao.findBetweenTimestamps(mini, maxi);
    }
}
