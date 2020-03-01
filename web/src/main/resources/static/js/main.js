$(document).ready(function () {
    $('form').submit(function () {
        const softwareName = document.getElementById('softwareNameInput').value;
        if (isNotBlank(softwareName)) {
            window.location.replace("http://localhost:8083/client-api-management/saml/register?registrationRequest=ewoKICAiY2xpZW50TmFtZSI6ICJOZWpsZXDFocOtIHByb2R1a3QiLAoKICAiY2xpZW50TmFtZUVuIjogIkJlc3QgcHJvZHVjdCIsCgogImFwcGxpY2F0aW9uVHlwZSI6ICJ3ZWIiLAoKICAicmVkaXJlY3RVcmlzIjogWwoKICAgICJodHRwOi8vbG9jYWxob3N0Ojg4ODgvdHJhbnNhY3Rpb25zIgoKICBdLAoKICAic2NvcGUiOiBbCgogICAgImFkYWEiCgogIF0sCgogICJzb2Z0d2FyZVN0YXRlbWVudCI6ICJleUpoYkdjaU9pSlNVekkxTmlKOS5leUpwYzNNaU9pSkxiMjFsY3NTTmJzT3RJRUpoYm10aElITXVjaTV2TGlJc0ltbGhkQ0k2TVRVNE1EZ3lORGd4TUN3aVpYaHdJam94TlRrMk5UUTVOakV3TENKMlpXNWtiM0pPWVcxbElqb2lRbXh2YXpNM0lITXVjaTV2TGlJc0luTnZablIzWVhKbFRtRnRaU0k2SWs1bGFteGxjTVdodzYwZ2NISnZaSFZyZENJc0luTnZablIzWVhKbFRtRnRaVVZ1SWpvaVFtVnpkQ0J3Y205a2RXTjBJaXdpYzI5bWRIZGhjbVZKWkNJNklqSTFZbVkwTVRGbExXWmxaR0V0TkdFME9TMWhZVGxtTFdSbU5qZ3pORGd3TWpZMk5DSXNJbk52Wm5SM1lYSmxWbVZ5YzJsdmJpSTZJakV1TUNJc0luTnZablIzWVhKbFZYSnBJam9pYUhSMGNEb3ZMM2QzZHk1emIyWjBMWGRoY21VdVkzb2lMQ0p5WldScGNtVmpkRlZ5YVhNaU9sc2lhSFIwY0RvdkwyeHZZMkZzYUc5emREbzRPRGc0TDNSeVlXNXpZV04wYVc5dWN5SmRMQ0owYjJ0bGJrVnVaSEJ2YVc1MFFYVjBhRTFsZEdodlpDSTZJbU5zYVdWdWRGOXpaV055WlhSZmNHOXpkQ0lzSW1keVlXNTBWSGx3WlhNaU9sc2lZWFYwYUc5eWFYcGhkR2x2Ymw5amIyUmxJbDBzSW5KbGMzQnZibk5sVkhsd1pYTWlPbHNpWTI5a1pTSmRMQ0p5WldkcGMzUnlZWFJwYjI1Q1lXTnJWWEpwSWpvaWFIUjBjRG92TDNkM2R5NXpiM1JtTFhkaGNtVXVZM292Y21WbmFYTjBaWElpTENKamIyNTBZV04wY3lJNld5SmxiV0ZwYkRvZ2RHVnpkRUIwWlhOMExtOXlaeUpkTENKc2IyZHZWWEpwSWpvaWFIUjBjRG92TDNkM2R5NXpiM1JtTFhkaGNtVXVZM292Ykc5bmJ5NXdibWNpTENKMGIzTlZjbWtpT2lKb2RIUndPaTh2ZDNkM0xuTnZkR1l0ZDJGeVpTNWplaTkwYjNNaUxDSndiMnhwWTNsVmNta2lPaUpvZEhSd09pOHZkM2QzTG5OdmRHWXRkMkZ5WlM1amVpOXdiMnhwWTNraWZRLkVNejMxeUNEYWlUTkpUSTZ4UVk5ZDRzSGk4YU52aXVMRFh0TWRRc2wxLWRQajNpNTlVUjVkNThXOFl1V1VWUGZsSTc5T3JYQnFGTkVscUNlNk1xUEI5STBWODloVXJWbk9FdHNxM1hFdlB6SWFlZHBWVFE4Sk9uZWRtclFpMkRFWUlYc2RvLXd1VWk5WVdSS3ItMXlmZUlPdjBOVGFfQ0tqSHVzUzVESDlyYjlHb01Rcm84ekVLZmFlTUJSS2gyeDFqNmtJQUxobjhfOVhwcWtBb1RuQnQ5RUoyZWswckt1ZjRtQlhIYUx5bEJVRGN0cWRDdUdPZ19RUEktb1BsbFI2Q0t0R2tmb0FoZ0JyWFEzendhWGRXd0JjMjRsUTYzUkFSUmN2UGlidFNsYUZxZlNuUThiU0pfMF9mVzNnaXRsTEl5eVJqOE1QcmJaZWFkc0Fjbkt6QSIsCgogICJlbmNyeXB0aW9uQWxnIjogIkFFUy0yNTYiLAogICJlbmNyeXB0aW9uS2V5IjogIlJpMUtZVTVrVW1kVmExaHdNbk0xZGpoNUwwSS9SU2hJSzB0aVVHVlRhRlk9IgoKfQ==");
        } else {
            window.location.replace("error");
        }

        return false;
    });

    function isNotBlank(str) {
        return str && !(/^\s*$/.test(str));
    }
});