import java.util.*;
import org.example.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.example.Mage;
import org.example.MageController;
import org.example.MageRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MageControllerTester {
    MageRepository mageRepository;
    MageController mageController;

    @BeforeEach
    public void setup() {
        mageRepository = mock(MageRepository.class);
        mageController = new MageController(mageRepository);
    }

    @Test
    public void deleteExistingMage_ReturnsDone(){
        String mage = "mag";
        doNothing().when(mageRepository).delete(mage);
        String result = mageController.delete(mage);
        assertThat(result).isEqualTo("done");
    }

    @Test
    public void deleteNotExistingMage_ReturnsNotFound(){
        doThrow(new IllegalArgumentException()).when(mageRepository).delete("mag");
        String result = mageController.delete("mag");
        assertThat(result).isEqualTo("not found");
    }

    @Test
    public void findNotExistingMage_ReturnsNotFound(){
        when(mageRepository.find("mag")).thenReturn(Optional.empty());
        String result = mageController.find("mag");
        assertThat(result).isEqualTo("not found");
    }

    @Test
    public void findExistingMage_ReturnsMage(){
        Mage mage = new Mage("mag", 1);
        when(mageRepository.find("mag")).thenReturn(Optional.of(mage));
        String result = mageController.find("mag");
        assertThat(result).isEqualTo(mage.toString());
    }

    @Test
    public void saveNotExistingMage_ReturnsDone(){
        when(mageRepository.find("mag")).thenReturn(Optional.empty());
        String result = mageController.save("mag", 1);
        assertThat(result).isEqualTo("done");
    }

    @Test
    public void saveExistingMage_ReturnsBadRequest(){
        doThrow(new IllegalArgumentException()).when(mageRepository).save(new Mage("mag", 10));
        assertThat(mageController.save("mag", 10)).isEqualTo("bad request");
    }
}