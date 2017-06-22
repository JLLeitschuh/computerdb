package com.ebiz.computerdatabase.servlet;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.mapper.CompanyDTOMapper;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.mapper.PageRequestMapper;
import com.ebiz.computerdatabase.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ebiz on 21/06/17.
 */
@RequestMapping(value="/api/computers")
@RestController
public class ComputerRestController {

    @Autowired
    ComputerService computerService;


    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<ComputerDTO> getComputers(@RequestParam(value="page", required = false) String page,@RequestParam(value="search", required = false) String search,@RequestParam(value="orderby", required = false) String orderby,@RequestParam(value="sort", required = false) String sort,@RequestParam(value="itemNumber", required = false) String itemNumber) {//REST Endpoint.

        Map<String,String> map = new HashMap<String,String>();
        map.put("page",page);
        map.put("itemNumber",itemNumber);
        map.put("orderby",orderby);
        map.put("sort",sort);

        return ComputerDTOMapper.createComputerDTOList(computerService.getPage(PageRequestMapper.pageRequestMapper(map),search).getContent());
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ComputerDTO getComputerById(@PathVariable String id) {//REST Endpoint.

        return ComputerDTOMapper.createComputerDTO(computerService.getComputerById(id));
    }


}
