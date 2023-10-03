package capstone.Extras;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Plays the Audio on Command */
public class AudioPlayer {
  private Clip clip;

  public void playAudio(String filePath) {
    try {

      File audioFile = new File(filePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

      clip = AudioSystem.getClip();
      clip.open(audioInputStream);

      clip.start();
    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  /** Stops the Audio on Command */
  public void stopAudio() {
    if (clip != null && clip.isRunning()) {
      clip.stop();
    }
  }
}
