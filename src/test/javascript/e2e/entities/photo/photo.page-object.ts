import { element, by, ElementFinder } from 'protractor';

export class PhotoComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-photo div table .btn-danger'));
    title = element.all(by.css('jhi-photo div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PhotoUpdatePage {
    pageTitle = element(by.id('jhi-photo-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    imageInput = element(by.id('file_image'));
    albumSelect = element(by.id('field_album'));
    calbumSelect = element(by.id('field_calbum'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setImageInput(image) {
        await this.imageInput.sendKeys(image);
    }

    async getImageInput() {
        return this.imageInput.getAttribute('value');
    }

    async albumSelectLastOption() {
        await this.albumSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async albumSelectOption(option) {
        await this.albumSelect.sendKeys(option);
    }

    getAlbumSelect(): ElementFinder {
        return this.albumSelect;
    }

    async getAlbumSelectedOption() {
        return this.albumSelect.element(by.css('option:checked')).getText();
    }

    async calbumSelectLastOption() {
        await this.calbumSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async calbumSelectOption(option) {
        await this.calbumSelect.sendKeys(option);
    }

    getCalbumSelect(): ElementFinder {
        return this.calbumSelect;
    }

    async getCalbumSelectedOption() {
        return this.calbumSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class PhotoDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-photo-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-photo'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
