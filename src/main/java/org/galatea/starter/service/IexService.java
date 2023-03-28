package org.galatea.starter.service;


import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * A layer for transformation, aggregation, and business required when retrieving data from IEX.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IexService {
  /**
   * Reads file and extracts API token.
   *
   */
  @Value("${authorization.apiToken}")
  private String token;


  @NonNull
  private IexClient iexClient;


  /**
   * Get all stock symbols from IEX.
   *
   * @return a list of all Stock Symbols from IEX.
   */
  public List<IexSymbol> getAllSymbols() {
    return iexClient.getAllSymbols(token);
  }

  /**
   * Get the last traded price for each Symbol that is passed in.
   *
   * @param symbols the list of symbols to get a last traded price for.
   * @return a list of last traded price objects for each Symbol that is passed in.
   */
  public List<IexLastTradedPrice> getLastTradedPriceForSymbols(final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      System.out.println("Whoa there partner, you need a symbol.");
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbol(symbols.toArray(new String[0]), token);
    }
  }

  /**
   * Get the historical prices for a symbol from a given date.
   *
   * @param symbols the list of symbols to get a last traded price for.
   * @param from date from which the historical prices begin.
   * @return a list of prices for the symbol passed in beginning from a specific date.
   */
  public List<IexHistoricalPrices> getHistoricalPricesFrom(final Date from, final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      System.out.println("Whoa there partner, you need a symbol.");
      return Collections.emptyList();
    } else {
      return iexClient.getHistoricalPricesFrom(from, symbols.toArray(new String[0]), token);
    }
  }
}
