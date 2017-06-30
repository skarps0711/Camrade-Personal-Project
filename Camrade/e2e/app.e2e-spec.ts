import { CamradePage } from './app.po';

describe('camrade App', function() {
  let page: CamradePage;

  beforeEach(() => {
    page = new CamradePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
