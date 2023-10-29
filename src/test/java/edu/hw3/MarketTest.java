package edu.hw3;

import edu.hw3.StockProblem.MarketImplementation;
import edu.hw3.StockProblem.Stock;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MarketTest {
    @Test
    void TestMarket1() {
        MarketImplementation market = new MarketImplementation();

        market.add(new Stock(0));
        market.add(new Stock(1));
        market.add(new Stock(2));
        Stock result = market.mostValuableStock();

        Stock expectedResult = new Stock(2);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void TestMarket2() {
        MarketImplementation market = new MarketImplementation();

        market.add(new Stock(300));
        market.add(new Stock(100));
        market.add(new Stock(0));
        market.add(new Stock(20));
        Stock result = market.mostValuableStock();

        Stock expectedResult = new Stock(300);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void TestMarketNegativePriceStock() {
        MarketImplementation market = new MarketImplementation();

        assertThatIllegalArgumentException().isThrownBy(() -> market.add(new Stock(-1)));
    }
}
