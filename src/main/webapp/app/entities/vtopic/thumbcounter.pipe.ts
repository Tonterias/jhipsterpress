import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'thumbcounter'
})
export class ThumbCounterPipe implements PipeTransform {
    transform(array: any, propName?, propValue?): any {
        if (!array || !Array.isArray(array)) {
            return null;
        }
        if (!(propName || propValue)) {
            return array.length;
        }
        if (propName) {
            return array.filter(item => (typeof propValue !== 'undefined' ? item[propName] === propValue : item[propName])).length;
        }

        return array.filter(item => item === propValue).length;
    }
}
