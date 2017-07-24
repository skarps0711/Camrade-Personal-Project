import { Directive, Input, ElementRef } from '@angular/core'

@Directive({
    selector: '[highlightNotifications]'
})

export class HighlightNotificationsDirective {

    @Input('highlightNotifications') notificationStatus: string;

    constructor(private el: ElementRef) {
        el.nativeElement.style.backgroundColor = 'yellow';
    }

}