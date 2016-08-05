Atlassian Connect Play Seed
===========================

[![Build Status](https://travis-ci.org/toolsplus/atlassian-connect-play-seed.svg?branch=master)](https://travis-ci.org/toolsplus/atlassian-connect-play-seed)
[![codecov](https://codecov.io/gh/toolsplus/atlassian-connect-play-seed/branch/master/graph/badge.svg)](https://codecov.io/gh/toolsplus/atlassian-connect-play-seed)


This project serves as a starter for Atlassian Connect add-ons
based on [Atlassian Connect Play](atlassian-connect-play).

## Quick start

Start by cloning this repository
 
 
### ngrok
To install a Atlassian Connect add-on on a Cloud instance it has to be served 
over HTTPS. This can easily be done using [ngrok](ngrok). Make sure you have it
installed and a tunnel started using
       
       ngrok http 9000
       
ngrok will display the base URL for your add-on. Use it to configure the `AC_BASE_URL`
parameter in the [run configuration](#run-configuration).

### Database

This project is configured to use Postgres backend storage via 
[Atlassian Connect Play Slick](atlassian-connect-play-slick). Follow the guide
there on how to configure a different database system if you wish to use something
else.

### Run configuration

To start the add-on you will need to configure a few parameters.

    AC_BASE_URL=https://my-host.ngrok.io
    AC_ALLOW_REINSTALL_MISSING_HOST=true
    SLICK_DBS_DEFAULT_DRIVER=slick.driver.PostgresDriver$
    SLICK_DBS_DEFAULT_DB_URL=jdbc:postgresql:acpsseed
    SLICK_DBS_DEFAULT_DB_DRIVER=org.postgresql.Driver
    SLICK_DBS_DEFAULT_DB_USER=sa
    SLICK_DBS_DEFAULT_DB_PASSWORD=


## Contributing
 
Pull requests are always welcome. Please follow the [contribution guidelines](CONTRIBUTING.md).
    
## License

atlassian-connect-play-seed is licensed under the **[Apache License, Version 2.0][apache]** (the
"License"); you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[atlassian-connect-play]: https://github.com/toolsplus/atlassian-connect-play
[atlassian-connect-play-slick]: https://github.com/toolsplus/atlassian-connect-play-slick
[ngrok]: https://ngrok.com/
[apache]: http://www.apache.org/licenses/LICENSE-2.0