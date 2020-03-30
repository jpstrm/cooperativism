package br.com.cooperativism.converter;

import br.com.cooperativism.base.AbstractUnitTest;
import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.enums.SessionStatusEnum;
import br.com.cooperativism.mock.SessionDtoMock;
import br.com.cooperativism.response.session.SessionResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class SessionConverterTest extends AbstractUnitTest {

  @InjectMocks
  private SessionConverter sessionConverter;

  @Test
  void shouldConvertToResponse() {
    SessionDto sessionDto = SessionDtoMock.getRandom(10);

    SessionResponse response = sessionConverter.toResponse(sessionDto);

    assertNotNull(response);
    assertEquals(SessionStatusEnum.STARTED, SessionStatusEnum.valueOf(response.getStatus()));
    assertNotNull(response.getTopic());
  }

}
