import { element, by, ElementFinder } from 'protractor';

export class CmessageComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-cmessage div table .btn-danger'));
    title = element.all(by.css('jhi-cmessage div h2#page-heading span')).first();

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

export class CmessageUpdatePage {
    pageTitle = element(by.id('jhi-cmessage-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    messageTextInput = element(by.id('field_messageText'));
    isDeliveredInput = element(by.id('field_isDelivered'));
    csenderSelect = element(by.id('field_csender'));
    creceiverSelect = element(by.id('field_creceiver'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setMessageTextInput(messageText) {
        await this.messageTextInput.sendKeys(messageText);
    }

    async getMessageTextInput() {
        return this.messageTextInput.getAttribute('value');
    }

    getIsDeliveredInput() {
        return this.isDeliveredInput;
    }

    async csenderSelectLastOption() {
        await this.csenderSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async csenderSelectOption(option) {
        await this.csenderSelect.sendKeys(option);
    }

    getCsenderSelect(): ElementFinder {
        return this.csenderSelect;
    }

    async getCsenderSelectedOption() {
        return this.csenderSelect.element(by.css('option:checked')).getText();
    }

    async creceiverSelectLastOption() {
        await this.creceiverSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async creceiverSelectOption(option) {
        await this.creceiverSelect.sendKeys(option);
    }

    getCreceiverSelect(): ElementFinder {
        return this.creceiverSelect;
    }

    async getCreceiverSelectedOption() {
        return this.creceiverSelect.element(by.css('option:checked')).getText();
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

export class CmessageDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-cmessage-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-cmessage'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
