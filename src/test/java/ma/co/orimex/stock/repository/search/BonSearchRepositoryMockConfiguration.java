package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of BonSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class BonSearchRepositoryMockConfiguration {

    @MockBean
    private BonSearchRepository mockBonSearchRepository;

}
