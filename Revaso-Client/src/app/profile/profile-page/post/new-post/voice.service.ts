// import { Injectable } from '@angular/core';
// @Injectable({
//     providedIn: 'root'
// })

// export class VoiceService {
   
//     message = document.querySelector('#message');
//      SpeechRecognition = SpeechRecognition;
//      SpeechGrammarList = SpeechGrammarList;
//      grammar = '#JSGF V1.0;'
//      recognition = new SpeechRecognition();
//      speechRecognitionList = new SpeechGrammarList();
//     speechRecognitionList.addFromString(grammar, 1);
//     recognition.grammars = speechRecognitionList;
//     recognition.lang = 'en-US';
//     recognition.interimResults = false;
//     recognition.onresult = function(event) {
//         var last = event.results.length - 1;
//         var command = event.results[last][0].transcript;
// ​
//        // message.textContent = 'Voice Input: ' + command + '.';
       
//       // document.getElementById("area").value="hahha";
       
// ​
       
//         document.querySelector('#area').value =  command ;
//     };
//     recognition.onspeechend = function() {
//         recognition.stop();
//     };
//     recognition.onerror = function(event) {
//         message.textContent = 'Error occurred in recognition: ' + event.error;
//     }        
//     document.querySelector('#btnGiveCommand').addEventListener('click', function(){
//         recognition.start();
//     });
// }