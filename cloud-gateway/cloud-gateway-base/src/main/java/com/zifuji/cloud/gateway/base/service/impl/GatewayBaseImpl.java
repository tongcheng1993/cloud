package com.zifuji.cloud.gateway.base.service.impl;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.gateway.base.service.GatewayBaseService;

@Service
public class GatewayBaseImpl implements GatewayBaseService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    private void notifyChanged() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }


}
