package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("로또 티켓")
public class LottoTicketTest {

    @Test
    @DisplayName("로또 발급")
    public void createLottoTicket() throws Exception {
        //given
        LottoTicket lottoTicket = LottoMachine.createLottoTicket();

        //when

        //then
        assertThat(lottoTicket).isNotNull();
    }

    @Test
    @DisplayName("로또 번호 6개인지 확인")
    public void checkLottoNumberSize() throws Exception {
        //given
        LottoTicket lottoTicket = LottoMachine.createLottoTicket();

        //when
        List<LottoNumber> numbers = lottoTicket.lottoNumber();

        //then
        assertThat(numbers.size()).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,", "1,2,3,4,5"})
    @DisplayName("로또 번호 6개 미만 예외 확인")
    public void checkLottoNumberSizeException(String input) throws Exception {
        //given

        //when
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoMachine.createLottoTicket(input);
        });

        //then
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,1,2,3,4,5", "1,2,3,4,5,5"})
    @DisplayName("로또 번호 중복으로 티켓 발생 예외 확인")
    public void checkLottoTicketOverlapNumberException(String input) throws Exception {
        //given
        
        //when
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoMachine.createLottoTicket(input);
        });
        
        //then
        
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,1,2,3,4,45", "1,2,3,4,5,46"})
    @DisplayName("로또 번호 1 ~ 45 벗어날 시 예외 확인")
    public void lottoNumberSizeOutException(String input) throws Exception {
        //given

        //when
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoMachine.createLottoTicket(input);
        });
    }
}
