import React, { Component } from 'react';
import FormComponent from './FormComponent.js';
import PlaylistsComponent from './PlaylistsComponent.js';
import * as ChannelActions from '../actions/ChannelActions.js';
import ChannelStore from '../stores/ChannelStore.js';



class ClientComponent extends Component {
    constructor(){
        super();
        console.log('construktor');
        console.log(ChannelStore.getPlaylists());

    }





    render() {
    console.log(ChannelStore.getConfirm());
        return (
            <section id="header">
                <div className="container">
                    <FormComponent/>
                    <h4>Last 5 playlists created:</h4>

                         <PlaylistsComponent/>


                </div>

            </section>
        );
    }
}

export default ClientComponent;
