package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/trades")
public class StockTradeRestController {

    @Autowired
    private StockTradeService stockTradeService;

    @PostMapping
    public ResponseEntity<StockTrade> createStockTrade(@RequestBody StockTrade stockTrade){
        StockTrade createdStockTrade = stockTradeService.create(stockTrade);
        return new ResponseEntity<StockTrade>(createdStockTrade, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockTrade> > getStockTrades(){
        List<StockTrade> stockTrades = stockTradeService.getStockTrades();
        return ResponseEntity.ok(stockTrades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockTrade> getStockTrades(@PathVariable("id") Integer id){
        StockTrade stockTrade = null;
        try {
            stockTrade = stockTradeService.getStockTrade(id);
        } catch (StockNotFoundException e) {
            return new ResponseEntity<StockTrade>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stockTrade);
    }

    @RequestMapping(method = {DELETE, PUT, PATCH}, path = "/{id}")
    public ResponseEntity<StockTrade> stockTradeRequest(@PathVariable("id") Integer id){
        return new ResponseEntity<StockTrade>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
