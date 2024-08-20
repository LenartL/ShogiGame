import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {RxStompService} from "./rx-stomp/rx-stomp.service";
import {rxStompServiceFactory} from "./rx-stomp/rx-stomp-service-factory";

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory
    }]
};
