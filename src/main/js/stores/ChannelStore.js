import { EventEmitter } from "events";
import * as ChannelActions from '../actions/ChannelActions.js';

import dispatcher from "../dispatcher";



class ChannelStore extends EventEmitter {

    constructor() {
        super();
        this.channelStatus;
        this.confirm=false;
        this.playlists;

    }


    setPlaylist(playlists){
        console.log('setPlaylist');
        this.playlists=playlists;
    }

    getPlaylists(){
        return this.playlists;
    }

    setChannelStatus(channelExist){
        this.channelStatus=channelExist;
    }

    getChannelStatus(){
        return this.channelStatus;
    }


    setConfirm(confirm){
        this.confirm=confirm;
    }

    getConfirm(confirm){
        return this.confirm;
    }

    handleActions(action){
        switch(action.type) {

            case "ChannelExisting": {
                console.log(action.data.channelExisting);

                this.setChannelStatus(action.data.channelExisting);
                console.log(this.getChannelStatus());
                if(action.data.channelExisting==true){
                    ChannelActions.getCreatedPlaylists();
                }else {
                    this.setConfirm(true);
                }
                this.emit("change");
                console.log('w channel existing');
                console.log(this.getChannelStatus());
                break;
            }
            case "ConfirmChannel":{

                this.setConfirm(true);
                this.emit("change");


                break;
            }
            case "ChannelPlaylists":{
                console.log('cannelPlaylistemit');
                console.log(action.json);
                this.setPlaylist(action.json);
                this.emit("change2");

                break;
            }

        }

    }

}


const channelStore = new ChannelStore;
dispatcher.register(channelStore.handleActions.bind(channelStore));
export default channelStore;