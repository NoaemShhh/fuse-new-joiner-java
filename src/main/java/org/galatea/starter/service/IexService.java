package org.galatea.starter.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrices;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
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
  String token;

  {
    try {
      token = Files.readString(Path.of(
          "C:\\Users\\noaem\\Downloads\\fuse-new-joiner-java-develop"
              + "\\fuse-new-joiner-java-develop\\src\\main\\resources\\Secrets.txt"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

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
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbols(symbols.toArray(new String[0]), token);
    }
  }

  /**
   * Get the historical prices for a symbol from a given date.
   *
   * @param symbols the list of symbols to get a last traded price for.
   * @param from date from which the historical prices begin.
   * @return a list of prices for the symbol passed in beginning from a specific date.
   */
  public List<IexHistoricalPrices> getHistoricalPricesFrom(final String from,
      final String symbols) {
    return iexClient.getHistoricalPricesFrom(from, symbols, token);
  }
}
