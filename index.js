
import { NativeModules } from 'react-native';

const { SplashViewer } = NativeModules;

export default {
 
    show : ()=> {

        SplashViewer.show();
    },

    hide : ()=> {

        SplashViewer.hide();
    }
};
