import React, { Component } from 'react';
import ChannelStore from '../stores/ChannelStore.js';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';






class PlaylistsComponent extends Component {
    constructor(){
        super();
        this.state=this._getPlaylistsState();
    }

    _getPlaylistsState() {
        console.log('getplaylistState');
        console.log(ChannelStore.getPlaylists())
        return {
            channelExist:ChannelStore.getPlaylists()


        };
    }

    onChange() {
        this.setState(this._getPlaylistsState);
    }

    componentDidMount() {

        ChannelStore.on("change2", ()=>{
            console.log('onchange playlist');

            this.onChange();

        })

    }
    componentWillUnmount(){
        ChannelStore.removeListener("change2", ()=>{
            this.onChange();
        })
    }





    render() {
        console.log(this.state);
        return (
<div>
    {this.state.channelExist!=undefined ? <div className="col-xs-10 col-xs-offset-1 col-8 col-offset-2 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-6 col-xl-offset-3">
            <BootstrapTable data={this.state.channelExist} striped hover>
            <TableHeaderColumn isKey dataField='title'>Title</TableHeaderColumn>
            <TableHeaderColumn dataField='description'>Description</TableHeaderColumn>
            <TableHeaderColumn     dataField="playlistId" dataAlign="center" dataFormat={format}
            >Check your playlist</TableHeaderColumn>
        </BootstrapTable></div>  : <div></div>

}


</div>



        );
    }
}
function format(cell, row){

    return `<a href=${cell} <i  class='glyphicon glyphicon-log-in'></i> </a>`;
}


export default PlaylistsComponent;
