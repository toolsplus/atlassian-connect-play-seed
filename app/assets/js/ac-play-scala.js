import $ from "jquery";

/**
 * Sets up the page token by reading it from meta attributes and configuring AJAX calls.
 *
 * @param token Token to send with each AJAX call.
 */
function setupPageToken(token) {
    $.ajaxSetup({
        headers: {"Authorization": "JWT " + token}
    });
}

setupPageToken($("meta[name=token]").attr("content"));

/**
 * After setting up the page token initially, each subsequent AJAX call will refresh the token.
 * If this wouldn't happen, the token expires and the back-end server will not accept it anymore.
 */
$(document).ajaxComplete((e, xhr) => {
    if (xhr) {
        let newToken = xhr.getResponseHeader("Authorization").substr(4);
        if (newToken) setupPageToken(newToken)
    }
});
