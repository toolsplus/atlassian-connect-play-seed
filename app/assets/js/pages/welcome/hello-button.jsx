import $ from "jquery";
import React, {Component} from "react";
import {Button} from "aui-react";

export class HelloButton extends Component {

    handleClick() {
        console.log("You clicked me! Try to get Hello message...");
        $.ajax("/hello/message")
            .done((result) => {
              console.log("Received hello message:", result);
            })
            .fail((error) => {
                console.log("Getting hello message failed", error);
            });
    }

    render() {
        return (
            <Button type="primary" onClick={this.handleClick}>
                Say Hello
            </Button>
        );
    }

}
