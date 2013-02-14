# Create your views here.
from django.http import HttpResponse
from django.template import RequestContext, loader
from rango.models import Category, Page

def index(request):
        # select the appropriate template to use
        template = loader.get_template('rango/index.html')
        
        #Request all the categories
        cat_list = Category.objects.all()
        #Put the data into the context
        context = RequestContext(request, {'cat_list':cat_list})
        # render the template using the provided context and return as http response.
        return HttpResponse(template.render(context))
        
def about(request):
        # select the appropriate template to use
        template = loader.get_template('rango/about.html')
        # create and define the context. We don't have any context at the moment
        # but later on we will be putting data in the context which the template engine
        # will use when it renders the template into a page.
        context = RequestContext(request, {})
        # render the template using the provided context and return as http response.
        return HttpResponse(template.render(context))
