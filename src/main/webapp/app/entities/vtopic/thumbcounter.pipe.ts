import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'thumbcounter'
})
export class ThumbCounterPipe implements PipeTransform {
    transform(array: any, propName?, propValue?): any {
        if (!array || !Array.isArray(array)) {
            console.log('CONSOLOG: M:ThumbCounterPipe & O: array : ');
            return null;
        }
        if (!(propName || propValue)) {
            console.log('CONSOLOG: M:ThumbCounterPipe & O: propValue1 : ');
            return array.length;
        }
        if (propName) {
            console.log('CONSOLOG: M:ThumbCounterPipe & O: propName2 : ', propName, propValue, array);
            console.log(array.filter(item => item[propName] === propValue));
            return array.filter(item => (typeof propValue !== 'undefined' ? item[propName] === propValue : item[propName])).length;
        }
        console.log('CONSOLOG: M:registerQuestionThumbUp & O: this.vthumb : ', array.filter(item => item === propValue).length);
        return array.filter(item => item === propValue).length;
    }
}
