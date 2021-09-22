package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DefaultStockTradeService implements StockTradeService {

    StockTradeRepository stockTradeRepository;
    AtomicInteger idCounter;
    public DefaultStockTradeService(StockTradeRepository stockTradeRepository){
        this.idCounter = new AtomicInteger(1);
        this.stockTradeRepository = stockTradeRepository;
    }

    @Override
    public StockTrade create(StockTrade stockTrade) {
        stockTrade.setId(idCounter.incrementAndGet());
        return this.stockTradeRepository.save(stockTrade);
    }

    @Override
    public List<StockTrade> getStockTrades() {
        return this.stockTradeRepository.findAll();
    }

    @Override
    public StockTrade getStockTrade(Integer id) throws StockNotFoundException {
        Optional<StockTrade> stockTrade = stockTradeRepository.findById(id);
        if(stockTrade.isPresent()){
            return stockTrade.get();
        }
        throw new StockNotFoundException();
    }


}
