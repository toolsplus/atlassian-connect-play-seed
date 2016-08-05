import $ from "jquery";
import React, {Component} from "react";
import ReactDOM from "react-dom";
import {PageContent, Button} from "aui-react";
import {HelloButton} from "./hello-button"
import "../../ac-play-scala"

const HelloPage = () =>
    <PageContent>
        <h2>Atlassian Connect Play Scala Seed</h2>
        <HelloButton/>
    </PageContent>;

$(document).ready(() => {
    ReactDOM.render(<HelloPage/>, document.getElementById("add-on-page-container"))
});
