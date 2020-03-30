package br.com.cooperativism.converter;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.enums.SessionStatusEnum;
import br.com.cooperativism.mock.session.SessionDtoMock;
import br.com.cooperativism.response.session.SessionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SessionConverterTest {

  @InjectMocks
  private SessionConverter sessionConverter;

  @Spy
  private ModelMapper modelMapper = new ModelMapper();

  @Test
  public void shouldConvertToResponse() {
    SessionDto sessionDto = SessionDtoMock.getRandom(10);

    SessionResponse response = sessionConverter.toResponse(sessionDto);

    assertNotNull(response);
    assertEquals(SessionStatusEnum.STARTED, SessionStatusEnum.find(response.getStatus()).get());
    assertNotNull(response.getTopic());
  }

}
