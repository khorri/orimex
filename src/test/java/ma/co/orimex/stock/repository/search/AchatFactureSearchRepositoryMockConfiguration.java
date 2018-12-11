package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AchatFactureSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AchatFactureSearchRepositoryMockConfiguration {

    @MockBean
    private AchatFactureSearchRepository mockAchatFactureSearchRepository;

}
