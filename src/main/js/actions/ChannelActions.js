import dispatcher from "../dispatcher";
import fetch from 'node-fetch';
import ChannelStore from '../stores/ChannelStore.js';
import LoginStore from '../stores/LoginStore.js';
import $ from "jquery";




export function checkChannelExisting() {
    console.log('checkChannelExisting');
    var initModel={
        accessToken:LoginStore.getAccessToken()
    }

    $.ajax({
        url: "/YTcreator/start",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(initModel),
        success: function (data) {
            console.log(data)
            console.log("narcos")
           dispatcher.dispatch({
                type: "ChannelExisting",
                data

        })
    }});

    return
}



export function getCreatedPlaylists() {
    console.log('getCreatedPlaylists');
    var initModel={
        accessToken:LoginStore.getAccessToken()
    }


    $.ajax({
        url: "/YTcreator/start2",
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(initModel),
        success: function (json) {
            dispatcher.dispatch({
                type: "ChannelPlaylists",
                json

            })
        }});


}

export function confirm() {
    dispatcher.dispatch({
        type: "ConfirmChannel",

    });
}
