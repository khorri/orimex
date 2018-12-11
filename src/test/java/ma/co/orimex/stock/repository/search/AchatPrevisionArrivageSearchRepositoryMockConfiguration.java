package ma.co.orimex.stock.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AchatPrevisionArrivageSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AchatPrevisionArrivageSearchRepositoryMockConfiguration {

    @MockBean
    private AchatPrevisionArrivageSearchRepository mockAchatPrevisionArrivageSearchRepository;

}
