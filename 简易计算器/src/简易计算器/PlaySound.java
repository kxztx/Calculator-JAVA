package 简易计算器;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;

public class PlaySound {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlaySound.Play("F:\\java\\提示音.wav");
	}

	// 播放音频文件
	public static void Play(String fileurl) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
			AudioFormat aif = ais.getFormat();
			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			int nByte = 0;
			byte[] buffer = new byte[128];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, 128);
				if (nByte >= 0) {
					sdl.write(buffer, 0, nByte);
				}
			}
			sdl.stop();
		} catch (Exception e) {
		}
	}
}
