import { element, by, ElementFinder } from 'protractor';

export class CelebComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-celeb div table .btn-danger'));
    title = element.all(by.css('jhi-celeb div h2#page-heading span')).first();

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

export class CelebUpdatePage {
    pageTitle = element(by.id('jhi-celeb-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    celebNameInput = element(by.id('field_celebName'));
    uprofileSelect = element(by.id('field_uprofile'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCelebNameInput(celebName) {
        await this.celebNameInput.sendKeys(celebName);
    }

    async getCelebNameInput() {
        return this.celebNameInput.getAttribute('value');
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

export class CelebDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-celeb-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-celeb'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
