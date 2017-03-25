package br.com.brainwave.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.furb.api.furbspeech.FurbSpeech;

@Controller
public class HomeController {

	@RequestMapping(path = "/", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}
	
	@RequestMapping(path = "/teste", method = { RequestMethod.GET })
	public String teste() {
		return "teste";
	}
	
	@RequestMapping(path = "/textToSpeech", method = { RequestMethod.POST })
	public String textToSpeech(@RequestParam String text) {
		FurbSpeech speech = new FurbSpeech();
		
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(speech.text(text).to("tts.wav").speech());
			Clip oClip;
			oClip = AudioSystem.getClip();
			oClip.open(audio);
	        oClip.loop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
}
