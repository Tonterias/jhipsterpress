import { element, by, ElementFinder } from 'protractor';

export class VanswerComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-vanswer div table .btn-danger'));
    title = element.all(by.css('jhi-vanswer div h2#page-heading span')).first();

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

export class VanswerUpdatePage {
    pageTitle = element(by.id('jhi-vanswer-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    urlVanswerInput = element(by.id('field_urlVanswer'));
    acceptedInput = element(by.id('field_accepted'));
    userSelect = element(by.id('field_user'));
    vquestionSelect = element(by.id('field_vquestion'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setUrlVanswerInput(urlVanswer) {
        await this.urlVanswerInput.sendKeys(urlVanswer);
    }

    async getUrlVanswerInput() {
        return this.urlVanswerInput.getAttribute('value');
    }

    getAcceptedInput() {
        return this.acceptedInput;
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
    }

    async vquestionSelectLastOption() {
        await this.vquestionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async vquestionSelectOption(option) {
        await this.vquestionSelect.sendKeys(option);
    }

    getVquestionSelect(): ElementFinder {
        return this.vquestionSelect;
    }

    async getVquestionSelectedOption() {
        return this.vquestionSelect.element(by.css('option:checked')).getText();
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

export class VanswerDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-vanswer-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-vanswer'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
