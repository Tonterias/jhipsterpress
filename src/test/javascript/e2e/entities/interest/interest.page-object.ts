import { element, by, ElementFinder } from 'protractor';

export class InterestComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-interest div table .btn-danger'));
    title = element.all(by.css('jhi-interest div h2#page-heading span')).first();

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

export class InterestUpdatePage {
    pageTitle = element(by.id('jhi-interest-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    interestNameInput = element(by.id('field_interestName'));
    uprofileSelect = element(by.id('field_uprofile'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setInterestNameInput(interestName) {
        await this.interestNameInput.sendKeys(interestName);
    }

    async getInterestNameInput() {
        return this.interestNameInput.getAttribute('value');
    }

    async uprofileSelectLastOption() {
        await this.uprofileSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async uprofileSelectOption(option) {
        await this.uprofileSelect.sendKeys(option);
    }

    getUprofileSelect(): ElementFinder {
        return this.uprofileSelect;
    }

    async getUprofileSelectedOption() {
        return this.uprofileSelect.element(by.css('option:checked')).getText();
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

export class InterestDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-interest-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-interest'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
