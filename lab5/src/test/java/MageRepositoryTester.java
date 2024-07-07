import org.example.Mage;
import org.example.MageRepository;
import org.junit.jupiter.api.Test;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTester {
    private MageRepository mageRepository = new MageRepository(new ArrayList<>());

    @Test
    public void deleteNotExistingMage_CausesIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> mageRepository.delete("mag"));
    }

    @Test
    public void findNotExistingMage_ReturnsOptionalEmpty() {
        Optional<Mage> result = mageRepository.find("mag");
        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    public void findExistingMage_ReturnsOptionalMage() {
        Mage mage = new Mage("mag", 1);
        mageRepository.save(mage);
        Optional<Mage> result = mageRepository.find("mag");
        assertThat(result).isEqualTo(Optional.of(mage));
    }

    @Test
    public void saveExistingMage_CausesIllegalArgumentException() {
        Mage mage = new Mage("mag", 1);
        mageRepository.save(mage);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> mageRepository.save(mage));
    }

}
