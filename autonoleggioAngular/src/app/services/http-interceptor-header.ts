import { SecurityMemory } from './../session-memory/security-memory';
import { Injectable } from "@angular/core";
import {
    HttpInterceptor,
    HttpRequest,
    HttpHandler,
    HttpEvent
  } from '@angular/common/http';
import { Observable } from "rxjs";

@Injectable()
export class HttpHeaderInterceptor implements HttpInterceptor {

  constructor (private securityMemory: SecurityMemory){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // add a custom header
    const customReq = request.clone({
      headers: request.headers.set('Authorization', this.securityMemory.token)
    });

    // pass on the modified request object
    return next.handle(customReq);
  }
}