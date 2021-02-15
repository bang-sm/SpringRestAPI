package com.toggle.study.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toggle.study.VO.Criteria;
import com.toggle.study.model.InsuprdDTO;
import com.toggle.study.model.InsuprdDestnDTO;
import com.toggle.study.model.SaleInsuprdDTO;
import com.toggle.study.serivce.InsuprdService;
import com.toggle.study.serivce.SaleInsuprdService;
import com.toggle.study.util.PageMaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@RestController
public class HelloController {
    
    @Autowired
    private InsuprdService insuprdService;
    @Autowired
    private SaleInsuprdService saleInsuprdService;
    @ResponseBody
    @GetMapping(value="/insuprd")
    public Map<String,List<InsuprdDTO>> Hello(Model model) throws Exception {
        
        Map<String,List<InsuprdDTO>> map =new HashMap<String,List<InsuprdDTO>>();

        List<InsuprdDTO> insu=insuprdService.selectInsuprd();

        map.put("list", insu);

        return map;
    }

    @ResponseBody
    @PostMapping(value="/test")
    public Map<String,InsuprdDestnDTO> Iprd(@RequestBody Map<String, Object> param) throws Exception {
        InsuprdDestnDTO infodata=insuprdService.saleInsuprd(param);

        Map<String,InsuprdDestnDTO> map =new HashMap<String,InsuprdDestnDTO>();

        map.put("iprd", infodata);

        return map;
    }

    @ResponseBody
    @PostMapping(value="/saleinsuprd")
    public Map<String,Object> SaleInsuprd(@RequestBody Criteria cri) throws Exception {

        System.out.println(cri.toString());
        //페이징
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(64);

        List<SaleInsuprdDTO> infodata=saleInsuprdService.saleInsuprd(cri);


        Map<String,Object> map =new HashMap<String,Object>();

        map.put("sl_prd", infodata);
        map.put("pageInfo", pageMaker);

        return map;
    }
    
}
