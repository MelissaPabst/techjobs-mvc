package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        ArrayList<HashMap<String, String>> jobs;

        int numJobs = 0;

        if(searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        if(!jobs.isEmpty()) {
            numJobs = jobs.size();
            model.addAttribute("jobs", jobs);
        }



        model.addAttribute("numJobs", numJobs);

        return "search";
    }

}
