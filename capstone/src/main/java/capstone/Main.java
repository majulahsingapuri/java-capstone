package capstone;

import capstone.Extras.AudioPlayer;
import capstone.Objects.Database;
import capstone.Views.LoginView;

/** Main Class that runs the program. */
public class Main {
  /**
   * Main function that is the starting point of the application. Initialises a new {@link Database}
   * and loads in the first view.
   *
   * @param args Arguments passsed to the App.
   */
  public static void main(String[] args) {
    new Database();

    LoginView view = new LoginView();
    AudioPlayer audioPlayer = new AudioPlayer();
    String music = "capstone/src/main/java/capstone/title.wav";
    audioPlayer.playAudio(music);
    view.print();
    Database.close();
    audioPlayer.stopAudio();
  }
}
