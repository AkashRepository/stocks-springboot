package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;

import java.util.List;

public interface StockTradeService {
    StockTrade create(StockTrade stockTrade);

    List<StockTrade> getStockTrades();

    StockTrade getStockTrade(Integer id) throws StockNotFoundException;
}
